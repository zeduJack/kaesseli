//
//  TransactionScreen.swift
//  ios
//
//  Created by Choesang Tenzin on 20.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct TransactionScreen: View {
    
    @EnvironmentObject var state: State
    
    let facade = Facade()
    
    let account: StartScreen.Account
    
    var body: some View {
        Text(account.kontostandLabel).font(.title)
            List {
                ForEach(account.transactions, id: \.self) {
                    transaction in
                    HStack {
                        Text (transaction.message)
                        Spacer()
                        Text (String(transaction.amount))
                    }
                }
            }.navigationTitle(account.accountLabel)
        
    }
}

/*
struct TransactionScreen_Previews: PreviewProvider {
    static var previews: some View {
        TransactionScreen()
    }
}*/
