//
//  CategoryImageMap.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class CategoryDataSourceDi {
    static let imageMap: [KotlinLong: String] = [
        // Daily
        1: "oar.2.crossed", // Maintenance
        2: "figure.socialdance", // Socila
        3: "candybarphone", // Phone
        4: "cross.case", // Medical
        5: "bus.fill", // Traffic
        6: "washer", // Groceries
        7: "cart", // Shopping
        8: "fork.knife", // Dinner
        // Activity
        9: "newspaper.circle", // Office
        10: "graduationcap", // Education
        11: "books.vertical", // Books
        12: "airplane", // Travel
        13: "popcorn", // Fun
        14: "baseball", // Sports
        // Family
        15: "pawprint.circle", // Pet
        16: "person.3.sequence.fill", // Parents
        17: "figure.2.and.child.holdinghands", // Child
        // Other
        18: "command", // Other
        19: "newspaper", // Tax
        // Income
        20: "questionmark.circle", // Other,
        21: "b.circle", // Borrow,
        22: "dollarsign.arrow.circlepath", // Stock,
        23: "f.circle", // Fund,
        24: "storefront", // Rent,
        25: "i.circle", // Invest,
        26: "clock.arrow.2.circlepath", // Parttime,
        27: "dollarsign.square", // Salary,
    ]
    
    static let instance = CategoryDataSource()
}
