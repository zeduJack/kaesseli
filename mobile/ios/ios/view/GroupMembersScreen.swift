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
    
    let userGroup: StartScreen.Group
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                List  {
                    ForEach(userGroup.members, id: \.self) {
                        member in NavigationLink(
                            destination: AccountScreen(member: member),
                            label: {
                                MemberView(member: member)
                            })
                    }.listRowBackground(Color.blue)
                    
                }.navigationTitle(userGroup.name)
            }
        }.padding()
            .foregroundColor(Color.white)
    }
}

/*
 struct GroupMembersScreen_Previews: PreviewProvider {
 static var previews: some View {
 // GroupMembersScreen()
 }
 }*/
