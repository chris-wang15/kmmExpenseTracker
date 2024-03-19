//
//  ExpenseTitleContainerVM.swift
//  iosApp
//
//  Created by Liwei Wang on 10/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

extension ExpenseTitleContainer {
    class ViewModel: ObservableObject {
        let expenseDataSource = DatabaseDi
            .instance
            .expenseDataSource
        @Published var selectedYear: Int
        @Published var selectedMonth: Int
        @Published var outcome: Double = 0.0
        @Published var income: Double = 0.0
        @Published var expenseList: Array<Expense> = []
        private var cancellables = Set<AnyCancellable>()
        
        init() {
            let date = DateTimeUtilDi.instance.now()
            self.selectedYear = Int(date.year)
            self.selectedMonth = Int(date.monthNumber)
            Publishers.CombineLatest3($selectedYear, $selectedMonth, $expenseList)
                        .sink { [weak self] year, month, _ in
                            self?.updateOutcome(year: year, month: month)
                            self?.updateIncome(year: year, month: month)
                        }
                        .store(in: &cancellables)
            
            DatabaseDi
                .instance
                .expenseDataSource
                .observeAllExpense()
                .watch { dataList in
                    self.expenseList = dataList as? Array<Expense> ?? []
                }
        }
        
        private func updateOutcome(year: Int, month: Int) {
            DispatchQueue.main.async {
                self.expenseDataSource.getOutcomeByDate(
                    year: Int32(year),
                    month: Int32(month)
                ) { outcomeAmont, error in
                    if outcomeAmont != nil {
                        self.outcome = Double(truncating: outcomeAmont!)
                    } else {
                        let eMsg = error?.localizedDescription ?? "error"
                        print("getOutcomeByDate failed \(eMsg)")
                    }
                }
            }
        }
        private func updateIncome(year: Int, month: Int) {
            DispatchQueue.main.async {
                self.expenseDataSource.getIncomeByDate(
                    year: Int32(year),
                    month: Int32(month)
                ) { incomeAmont, error in
                    if incomeAmont != nil {
                        self.income = Double(truncating: incomeAmont!)
                    } else {
                        let eMsg = error?.localizedDescription ?? "error"
                        print("getOutcomeByDate failed \(eMsg)")
                    }
                }
            }
        }
    }
}
