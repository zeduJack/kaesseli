//
//  MemberView.swift
//  ios
//
//  Created by Choesang Tenzin on 15.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct MemberView: View {
    
    let member: StartScreen.Member
    
    var body: some View {
        VStack {
            HStack {
                Label("", systemImage: "person.crop.circle")
                Spacer()
                Text(member.firstname)
            }
            Text(member.sumOfAccountsLabel)
        }
    }
}

