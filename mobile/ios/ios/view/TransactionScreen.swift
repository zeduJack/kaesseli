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
    
    let account: SharedAccountDto
    
    var body: some View {
        TabView {
            TransactionListView(account: account)
                .tabItem {
                    Label("Transactions", systemImage: "list.dash")
                }
            TransactionChartView(account: account)
                .tabItem {
                    Label("Chart", systemImage: "square.and.pencil")
                }
        }.foregroundColor(Color.white)
        
    }
}

/*
struct TransactionScreen_Previews: PreviewProvider {
    static var previews: some View {
        TransactionScreen()
    }
}*/
