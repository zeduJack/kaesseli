//
//  AccountView.swift
//  ios
//
//  Created by Choesang Tenzin on 27.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct AccountView: View {
    
    let account: SharedAccountDto
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Label("", systemImage: "rectangle.badge.person.crop")
                Text(account.displayName);
                Text("\(account.saldoLabel)");
            }
        }.padding()
        .foregroundColor(Color.white)
    }
}



