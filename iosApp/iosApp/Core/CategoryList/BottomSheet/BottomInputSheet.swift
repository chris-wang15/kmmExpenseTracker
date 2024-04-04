//
//  BottomInputSheet.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BottomInputSheet: View {
    let categoryId: KotlinLong
    let categoryInitTitle: String
    let onSaveCallback: (Expense) -> Void
    @State private var title = ""
    @State private var amount: Double = 0.0
    @State private var selectedDate = Date()
    private let formatter = NumberFormatter()
    private let isOutcome: Bool
    
    init(categoryId: KotlinLong,
         onSaveCallback: @escaping (Expense) -> Void) {
        self.categoryId = categoryId
        self.onSaveCallback = onSaveCallback
        self.categoryInitTitle = CategoryDataSourceDi
            .instance
            .getCategoryById(id: Int64(truncating: categoryId)).title
        self.isOutcome = CategoryDataSourceDi
            .instance
            .isOutcome(id: categoryId)
        self.title = categoryInitTitle
        formatter.numberStyle = .currency
    }

    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Spacer()
                Text("New \(categoryInitTitle)")
                    .font(.title)
                    .lineLimit(1)
                    .padding()
                Spacer()
            }
            
            
            Text("Title: \(title.isEmpty ? categoryInitTitle : title)")
                .font(.caption)
                .padding(.leading)
            TextField("\(categoryInitTitle)", text: $title)
                .padding()
                .textFieldStyle(RoundedBorderTextFieldStyle())
            let formattedAmount = AmountUtil.formatAmount(amount: amount)
            Text("Amount: \(formattedAmount)")
                .font(.caption)
                .padding(.leading)
            TextField("Amount", value: $amount, format: .currency(code: "USD"))
                .padding()
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .keyboardType(.numberPad)
            
            DatePicker("Select a date", selection: $selectedDate, displayedComponents: .date)
                .labelsHidden()
                .datePickerStyle(CompactDatePickerStyle())
                .padding()
            
            Button("Save", action: {
                onSaveCallback(
                    Expense(
                        id: nil,
                        title: title.isEmpty ? categoryInitTitle : title,
                        cost: isOutcome ? Double(-amount): Double(amount),
                        categoryId: Int64(truncating: self.categoryId),
                        time: DateTimeUtilDi.instance.parseLong(
                            epochMillis: Int64(
                                selectedDate.timeIntervalSince1970 * 1000
                            )
                        )
                    )
                )
            })
            .padding()
            
            Spacer()
        }
        .frame(maxHeight: .infinity)
        .edgesIgnoringSafeArea(.bottom)
    }
}

#Preview {
    BottomInputSheet(
        categoryId: 1,
        onSaveCallback: {_ in}
    )
}
