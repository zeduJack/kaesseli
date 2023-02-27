//
//  AccountScreen.swift
//  ios
//
//  Created by Choesang Tenzin on 15.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct AccountScreen: View {
    
    let member: StartScreen.Member
    
    var body: some View {
        Text(member.accountsLabel).font(.title)
        List {
            ForEach(member.accounts ?? [], id: \.self) {
                account in NavigationLink(destination: TransactionScreen(account: account),
                    label: { AccountView(account: account) })
            }.listRowBackground(Color.blue)
        }.navigationTitle(member.firstname)
    }
}

/*struct AccountScreen_Previews: PreviewProvider {
    static var previews: some View {
        AccountScreen()
    }
}*/
