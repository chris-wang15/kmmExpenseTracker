//
//  ExpenseListContainer.swift
//  iosApp
//
//  Created by Liwei Wang on 11/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct ExpenseListContainer: View {
    @StateObject private var viewModel = ExpenseListContainer.ViewModel()
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 4) {
                ForEach($viewModel.expenseList, id: \.id) { expense in
                    ExpenseViewHolder(expense: expense)
                        .background(.gray.opacity(0.1))
                        .clipShape(RoundedRectangle(cornerRadius: 20))
                }
            }
        }
    }
}


extension ExpenseListContainer {
    class ViewModel: ObservableObject {
        private var cancellables = Set<AnyCancellable>()
        @Published var expenseList: Array<Expense> = []
        
        init() {
            DatabaseDi
                .instance
                .expenseDataSource
                .observeAllExpense()
                .watch { dataList in
                    self.expenseList = dataList as? Array<Expense> ?? []
                }
        }
        
    }
}

#Preview {
    ExpenseListContainer()
}
