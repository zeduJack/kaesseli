
import SwiftUI
import common

struct StartScreen: View {
    
    let facade = Facade()
    
    @EnvironmentObject var state: State
    
    struct LoggedInUser: Decodable {
        var id: Int
        var firstname: String
        var lastname: String
        var username: String
        var email: String
        var groups: [Group]
        var groupLabel: String
    }
    
    struct Group: Codable, Equatable, Hashable {
        var id: Int
        var name: String
        var members: [Member]
        var membersDescription: String
        var membersTitle: String
    }
    
    struct Member: Codable, Equatable, Hashable {
        var id: Int
        var username: String
        var firstname: String
        var lastname: String
        var accounts: [Account]?
        var sumOfAccountsLabel: String
        var accountsLabel: String
        var paymentLabel: String
        var amoutnLabel: String
        var messageLabel: String
    }
    
    struct Account: Codable, Equatable, Hashable {
        var id: Int
        var type: String
        var saldo: Int?
        var displayName: String
        var transactions: [Transaction]
        var saldoLabel: String
        var accountLabel: String
        var kontostandLabel: String
        var paymentAccountDescription: String
    }
    
    struct Transaction: Codable, Equatable, Hashable {
        var id: Int
        var createdAt: String
        var amount: String
        var username: String
        var userFirstname: String
        var userLastname: String
        var debit: Bool
        var message: String
        var status: String
    }

    
    var body: some View {
        let loggedInUser = Bundle.main.decode(LoggedInUser.self, from: "start")
        let groups = state.appState.logedInUser.groups
        
        List {
            ForEach(loggedInUser.groups, id: \.self) {
                userGroup in
                NavigationLink(
                    destination: GroupMembersScreen(userGroup: userGroup),
                    label: {
                        GroupView(title: userGroup.name, count: userGroup.members.count)
                    }
                ).listRowBackground(Color.blue)
            }
        }.navigationTitle("Familien")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button("Log Out") { }
                }
                
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {}) {
                        Image(systemName: "plus")
                    }
                }
            }.navigationBarBackButtonHidden()
    }
    /*
     struct GroupMembersScreen_Previews: PreviewProvider {
     static var previews: some View {
     StartScreen()
     }
     }*/
    
}
