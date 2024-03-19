//
//  ExpenseDataSourceDi.swift
//  iosApp
//
//  Created by Liwei Wang on 10/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class DatabaseDi {
    static let instance: DatabaseModel = DatabaseDi().database
    private let database: DatabaseModel
    private init() {
        database = DatabaseModel()
        database.doInitDatabase()
    }
}
