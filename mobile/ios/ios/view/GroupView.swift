//
//  CardView.swift
//  ios
//
//  Created by Choesang Tenzin on 06.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct GroupView: View {
    let title: String
    let count: Int 
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Label("\(count)", systemImage: "person.3.fill")
                    .accessibilityLabel("\(count) attendees")
                Spacer()
                Text(title)
                    .accessibilityAddTraits(.isHeader)
                    .font(.headline)
                    .font(.caption)
            }
        }
        .padding()
        .foregroundColor(Color.white)
    }
}
