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
    
    let member: SharedUserGroupMemberDto
    
    var body: some View {
        Text(member.accountsLabel).font(.title)
        List {
            ForEach(member.accountsList, id: \.self) {
                SharedAccountDto in NavigationLink(
                    destination: TransactionScreen(account: SharedAccountDto),
                    label: { AccountView(account: SharedAccountDto) })
            }.listRowBackground(Color(.systemBlue))
        }.navigationTitle(member.firstname)
    }
}

/*struct AccountScreen_Previews: PreviewProvider {
    static var previews: some View {
        AccountScreen()
    }
}*/
