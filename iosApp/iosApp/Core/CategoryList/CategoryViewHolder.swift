//
//  CategoryViewHolder.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryViewHolder: View {
    let category: ExpenseCategory
    let onCLick: (KotlinLong) -> Void
    let selectedCategoryId: KotlinLong
    var body: some View {
        
        
        VStack{
            ZStack {
                RoundedRectangle(cornerRadius: 16)
                    .fill(
                        selectedCategoryId == category.id ? Color.blue.opacity(0.3) : Color.gray.opacity(0.3)
                    )
                    .frame(width: 50, height: 50)
                Image(systemName: CategoryDataSourceDi.imageMap[category.id!] ?? "questionmark.app")
                    .foregroundColor(.black)
            }
            .onTapGesture {
                onCLick(category.id!)
            }
            Text(category.title)
                .frame(maxWidth: 80)
                .lineLimit(1)
                .font(.caption)
        }
    }
}

#Preview {
    CategoryViewHolder(
        category: ExpenseCategory(id: 1, title: "Cars"),
        onCLick: {_ in },
        selectedCategoryId: 0
    )
}
