
import SwiftUI
import common

struct StartScreen: View {
    
    let facade = Facade()
    
    @EnvironmentObject var state: State
    
    var body: some View {
        List {
            ForEach(state.appState.logedInUser.groupsList, id: \.self) {
                SharedLogedInUserUserGroupDto in
                NavigationLink(
                    destination: GroupMembersScreen(userGroup: SharedLogedInUserUserGroupDto),
                    label: {
                        GroupView(title: SharedLogedInUserUserGroupDto.name, count: SharedLogedInUserUserGroupDto.members.count)
                    }
                ).listRowBackground(Color(.systemBlue))
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
