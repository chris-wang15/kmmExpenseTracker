//
//  AmountUtil.swift
//  iosApp
//
//  Created by Liwei Wang on 11/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct AmountUtil {
    static func formatAmount(amount: Double) -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        
        if let formattedAmount = formatter.string(from: NSNumber(value: amount)) {
            return formattedAmount
        } else {
            return ""
        }
    }
}

