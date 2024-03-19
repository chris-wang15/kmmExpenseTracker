//
//  ExpenseViewHolder.swift
//  iosApp
//
//  Created by Liwei Wang on 08/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ExpenseViewHolder: View {
    @Binding var expense: Expense
    var body: some View {
        HStack(alignment:.center) {
            ZStack {
                Circle()
                    .fill(Color.gray.opacity(0.3))
                    .frame(width: 50)
                
                Image(
                    systemName: CategoryDataSourceDi.imageMap[
                        KotlinLong(value: expense.categoryId)
                    ] ?? "questionmark.app"
                )
                    .foregroundColor(.black)
            }
            
            VStack(alignment: .leading) {
                Text(expense.title)
                Text(DateTimeUtilDi.instance.formatNoteDate(
                    dateTime: expense.time)
                )
                    .foregroundStyle(.gray)
            }
            
            Spacer()
            
            HStack(spacing: 3) {
                Text(AmountUtil.formatAmount(amount: expense.cost))
            }
        }
        .padding()
    }
}

#Preview {
    ExpenseViewHolder(
        expense: .constant(Expense(
            id: 1,
            title: "title",
            cost: 88.88,
            categoryId: 1,
            time: DateTimeUtilDi.instance.now()
        ))
    )
}
