//
//  OutcomeCategoryList.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryList: View {
    let list: [ExpenseCategory]
    let onClick: (KotlinLong) -> Void
    @Binding var selectedCategoryId: KotlinLong
    var body: some View {
        LazyVGrid(
            columns: Array(repeating: GridItem(),count: 4),
            spacing: 20) {
                ForEach(list, id: \.self) { item in
                    CategoryViewHolder(
                        category: item,
                        onCLick: onClick,
                        selectedCategoryId: $selectedCategoryId
                    )
                }
            }
            .padding()
    }
}

#Preview {
    CategoryList(
        list: CategoryDataSource().allDailyCategories,
        onClick: {_ in},
        selectedCategoryId: .constant(1)
    )
}
