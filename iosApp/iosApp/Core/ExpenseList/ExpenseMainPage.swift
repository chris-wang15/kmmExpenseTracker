//
//  ExpenseList.swift
//  iosApp
//
//  Created by Liwei Wang on 08/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ExpenseMainPage: View {

    var body: some View {
        NavigationStack {
            ZStack(alignment: .bottomTrailing) {
                VStack {
                    ExpenseTitleContainer()
                    
                    ExpenseListContainer()
                }
                NavigationLink(destination: CategoryMainPage()) {
                    Image(systemName: "plus")
                        .resizable()
                        .frame(width: 24, height: 24)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())
                }
                .padding()
            }
        }
    }
}

#Preview {
    ExpenseMainPage()
}
