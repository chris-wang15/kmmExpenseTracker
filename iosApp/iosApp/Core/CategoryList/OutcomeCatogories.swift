//
//  OutcomeCatogories.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct OutcomeCatogories: View {
    @Binding var selectedCategoryId: KotlinLong
    let onClick: (KotlinLong) -> Void
    var body: some View {
        ScrollView {
            VStack(alignment: .leading){
                Text("Daily")
                    .font(.body)
                    .foregroundStyle(.gray)
                    .padding(.leading)
                Divider()
                CategoryList(
                    list:CategoryDataSourceDi.instance.allDailyCategories,
                    onClick: self.onClick,
                    selectedCategoryId: self.$selectedCategoryId
                )
                
                Text("Activities")
                    .font(.body)
                    .foregroundStyle(.gray)
                    .padding(.leading)
                Divider()
                CategoryList(
                    list:CategoryDataSourceDi.instance.allActivityCategories,
                    onClick: self.onClick,
                    selectedCategoryId: self.$selectedCategoryId)
                
                Text("Family")
                    .font(.body)
                    .foregroundStyle(.gray)
                    .padding(.leading)
                Divider()
                CategoryList(
                    list:CategoryDataSourceDi.instance.allFamilyCategories,
                    onClick: self.onClick,
                    selectedCategoryId: self.$selectedCategoryId)
                
                Text("Other")
                    .font(.body)
                    .foregroundStyle(.gray)
                    .padding(.leading)
                Divider()
                CategoryList(
                    list:CategoryDataSourceDi.instance.allOtherCategories,
                    onClick: self.onClick,
                    selectedCategoryId: self.$selectedCategoryId)
            }
        }
    }
}

#Preview {
    OutcomeCatogories(selectedCategoryId: .constant(1), onClick: {_ in})
}
