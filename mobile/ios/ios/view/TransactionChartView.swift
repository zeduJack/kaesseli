//
//  TransactionChartView.swift
//  ios
//
//  Created by Choesang Tenzin on 18.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common
import Foundation
import Charts

struct TransactionChartView: View {
    
    let account: SharedAccountDto
    let green = Color(hue: 0.33, saturation: 0.81, brightness: 0.76)

    var body: some View {
        
        animatedChart()
        
        
    }
    
    @ViewBuilder
    func animatedChart() -> some View {
        Chart {
            ForEach(account.transactionsList, id: \.self) {
                item in
                BarMark(
                    x : .value("Date", Bundle.main.dateFromString(date: item.createdAt)),
                    y : .value("Amount", Bundle.main.convertToDouble(resultingSaldo: item.resultingSaldo))
                )
                .clipShape(RoundedRectangle(cornerRadius: 16))
                .foregroundStyle(green)
                
                LineMark(
                    x : .value("Date", Bundle.main.dateFromString(date: item.createdAt)),
                    y : .value("Amount", Bundle.main.convertToDouble(resultingSaldo: item.resultingSaldo))
                )
                .clipShape(RoundedRectangle(cornerRadius: 16))
                .foregroundStyle(green)
                .lineStyle(StrokeStyle(lineWidth: 3, dash: [5, 10]))
                
                PointMark(x : .value("Date", Bundle.main.dateFromString(date: item.createdAt)),
                          y : .value("Amount", Bundle.main.convertToDouble(resultingSaldo: item.resultingSaldo)))
                .foregroundStyle(green)
                .annotation {
                    Text(verbatim: item.chartLabel)
                        .font(.caption)
                }
            }
        }
 //       .chartXAxis {
 //           AxisMarks(values: .stride(by: .day)) { date in
 //               AxisValueLabel(format: .dateTime.day())
 //           }
 //       }
    }
    
    
}
