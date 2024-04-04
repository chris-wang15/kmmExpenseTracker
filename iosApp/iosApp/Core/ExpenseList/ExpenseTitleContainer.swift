//
//  ExpenseTitleContainer.swift
//  iosApp
//
//  Created by Liwei Wang on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ExpenseTitleContainer: View {
    @StateObject private var viewModel = ExpenseTitleContainer.ViewModel()
    
    var body: some View {
        ZStack {
            LinearGradient(
                gradient: Gradient(
                    stops: [
                        .init(color: Color.blue.opacity(1.0), location: 0),
                        .init(color: Color.blue.opacity(0.7), location: 0.8)
                    ]
            
                ),
                startPoint: .top,
                endPoint: .bottom)
            .edgesIgnoringSafeArea(.all)
            VStack {
                Text("EXPENSE RECORDS")
                    .font(.title)
                    .foregroundStyle(.white)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                HStack(alignment:.center) {
                    VStack(alignment: .leading) {
                        YearPicker(selectedYear: $viewModel.selectedYear)
                        MonthPicker(selectedMonth: $viewModel.selectedMonth)
                    }
                    .padding(.trailing, 20)
                    Spacer()
                    Divider()
                        .frame(height: 50)
                    VStack {
                        HStack {
                            Text("Income")
                                .font(.caption)
                                .foregroundStyle(.white)
                            Spacer()
                            Text("Expense")
                                .font(.caption)
                                .foregroundStyle(.white)
                        }
                        HStack {
                            Text(AmountUtil.formatAmount(amount: viewModel.income))
                                .font(.body)
                                .foregroundStyle(.white)
                            Spacer()
                            Text(AmountUtil.formatAmount(amount: viewModel.outcome))
                                .font(.body)
                                .foregroundStyle(.white)
                        }
                    }
                    .padding(.leading, 10)
                }
                .padding()
            }
        }
        .frame(height: 200)
    }
}

struct YearPicker: View {
    @Binding var selectedYear: Int
    var body: some View {
        Menu {
            Picker(selection: $selectedYear) {
                ForEach(2000 ..< 2050) { number in
                    Text("\(number)")
                        .font(.caption)
                        .tag(number)
                }
            } label: {}
        } label: {
            HStack {
                Text("\(String(selectedYear))")
                    .font(.caption)
                    .foregroundStyle(.white)
                Text(">")
                    .font(.caption)
                    .foregroundStyle(.white)
            }
            
        }
    }
}

struct MonthPicker: View {
    @Binding var selectedMonth: Int

    var body: some View {
        Menu {
            Picker(selection: $selectedMonth) {
                ForEach(1 ..< 13) { number in
                    Text(formatMonth(month: number))
                        .font(.caption)
                        .tag(number)
                }
            } label: {}
        } label: {
            HStack {
                Text(formatMonth(month: selectedMonth))
                    .foregroundStyle(.white)
                Text(">")
                    .font(.caption)
                    .foregroundStyle(.white)
            }
        }
    }
    
    func formatMonth(month: Int) -> String {
        switch month {
            case 1:
                return "JAN"
            case 2:
                return "FEB"
            case 3:
                return "MAR"
            case 4:
                return "APR"
            case 5:
                return "MAY"
            case 6:
                return "JUN"
            case 7:
                return "JUL"
            case 8:
                return "AUG"
            case 9:
                return "SEP"
            case 10:
                return "OCT"
            case 11:
                return "NOV"
            case 12:
                return "DEC"
            default:
                return "Invalid month"
            }
    }
}

#Preview {
    ExpenseTitleContainer()
}
