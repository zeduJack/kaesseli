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
    
    let member: SharedUserGroupMemberDto
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Label("", systemImage: "person.crop.circle")
                Text(member.sumOfAccountsLabel)
                Spacer()
                Text(member.firstname)
                    .accessibilityAddTraits(.isHeader)
                    .font(.headline)
                    .font(.caption)
            }
            
        }
    }
}

