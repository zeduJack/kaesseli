//
//  TransactionListView.swift
//  ios
//
//  Created by Choesang Tenzin on 18.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct TransactionListView: View {
    
    let account: SharedAccountDto
    
    
    
    
    var body: some View {
            List {
                ForEach(account.transactionsList, id: \.self) {
                    SharedTransactionDto in
                    HStack {
                        VStack(alignment: .leading) {
                                Text (SharedTransactionDto.message)
                                Text(Bundle.main.userVisibleDate(date: SharedTransactionDto.createdAt))
                                .font(.system(size: 10))
                        }
                        
                        Spacer()
                        Text(String(SharedTransactionDto.chartLabel))
                            .foregroundColor(calculateColor(isDebit: SharedTransactionDto.debit))
                    }
                }.listRowBackground(Color(.systemBlue))
            }.navigationTitle(account.accountLabel)
            
    }
    
    func calculateColor(isDebit: Bool) -> Color {
        return isDebit ? Color.red : Color.white;
    }
}
