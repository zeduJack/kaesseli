import SwiftUI
import common

struct LoginScreen: View {
    
    let facade = Facade()
    
    @EnvironmentObject var state: State
    
    var body: some View {
        let binding = Binding(
            get: { state.appState.login.username },
            set: { facade.changeLoginInput(username: $0)}
        )
        
        Section(header: Text("Kaesseli")
            .textCase(.uppercase)
            .font(.title)) {
                NavigationView() {
                    VStack {
                        TextField("Email", text: binding)
                            .autocapitalization(.none)
                            .disableAutocorrection(true)
                        if (state.appState.login.isDirty && !state.appState.login.isValid) {
                            Text(state.appState.login.loginInvalidMessage)
                        }
                        
                        NavigationLink(destination:StartScreen(), label: {
                            Text("Sign In")
                                .font(.headline)
                                .frame(width: 125, height: 35)
                        }).simultaneousGesture(TapGesture().onEnded{
                            facade.logIn()
                        })
                    }
                    .padding()
                    .textFieldStyle(.roundedBorder)
                }.accentColor(Color(.label ))
                
            }
    }
    
}
