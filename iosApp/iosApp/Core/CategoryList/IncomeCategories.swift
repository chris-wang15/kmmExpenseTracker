//
//  IncomeCategories.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct IncomeCategories: View {
    @Binding var selectedCategoryId: KotlinLong
    let onClick: (KotlinLong) -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading){
                Text("Income")
                    .font(.body)
                    .foregroundStyle(.gray)
                    .padding(.leading)
                Divider()
                CategoryList(
                    list:CategoryDataSourceDi.instance.allIncomeCategories,
                    onClick: self.onClick,
                    selectedCategoryId: self.$selectedCategoryId
                )
            }
        }
    }
}

#Preview {
    IncomeCategories(
        selectedCategoryId: .constant(1),
        onClick: { _ in})
}
