//
//  GroupMembersScreen.swift
//  ios
//
//  Created by Choesang Tenzin on 06.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct GroupMembersScreen: View {
    
    let userGroup: SharedLogedInUserUserGroupDto
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                List {
                    ForEach(userGroup.membersList, id: \.self) {
                        SharedUserGroupMemberDto in NavigationLink(
                            destination: AccountScreen(member: SharedUserGroupMemberDto),
                            label: {
                                MemberView(member: SharedUserGroupMemberDto)
                            })
                    }.listRowBackground(Color(.systemBlue))
                    
                }.navigationTitle(userGroup.name)
            }
        }.padding()
            .foregroundColor(Color.white)
    }
}
