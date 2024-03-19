//
//  CategoryTab.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryTabContainer: View {
    @State private var currentPage = 0
    @State private var selectedCategoryId: KotlinLong = 0
    let expenseDataSource = DatabaseDi
        .instance
        .expenseDataSource
    
    private var isShowingSheet: Bool {
        return Int(truncating: selectedCategoryId) > 0
        }

    private var isShowingSheetBinding: Binding<Bool> {
            return Binding<Bool>(
                get: { self.isShowingSheet },
                set: { _ in }
            )
        }
    
    var body: some View {
        let onClick: (KotlinLong) -> Void = { id in
            print("\(id) item clicked")
            selectedCategoryId = id
        }
        
        TabView(selection: $currentPage) {
            OutcomeCatogories(
                selectedCategoryId: self.$selectedCategoryId,
                onClick: onClick)
                .tag(0)
            IncomeCategories(
                selectedCategoryId: self.$selectedCategoryId,
                onClick: onClick
            )
                .tag(1)
        }
        .tabViewStyle(PageTabViewStyle(indexDisplayMode: .never))
        .sheet(isPresented: isShowingSheetBinding) {
            BottomInputSheet(
                categoryId: self.$selectedCategoryId,
                onSaveCallback: { expense in
                    print("\(expense) is saved")
                    DispatchQueue.main.async {
                        self.expenseDataSource.insertExpense(
                            expense: expense
                        ) { error in
                            if let error = error {
                                print("insertExpense error: \(error)")
                            }
                        }
                    }
                }
            )
                .presentationDetents([.medium])
                .onDisappear {
                    selectedCategoryId = 0
                }
        }
        
        PageIndicator(currentPage: $currentPage)
            .padding(.top, 10)
    }
}

struct PageIndicator: View {
    @Binding var currentPage: Int
    
    var body: some View {
        HStack(spacing: 10) {
            ForEach(0..<2) { index in
                Circle()
                    .frame(width: 8, height: 8)
                    .foregroundColor(
                        index == currentPage ? .blue : .gray)
            }
        }
    }
}

#Preview {
    CategoryTabContainer()
}
