import SwiftUI
import common

struct ContentView: View {
    let greet = Greeting().greeting()
    let facade = Facade()
    
    @EnvironmentObject var state: State
    
    
    var body: some View {
        let binding = Binding(
            get: { state.appState.login.username },
            set: { facade.changeLoginInput(username: $0, dirty: false)}
        )
        
        return VStack {
            TextField("Enter your name", text: binding)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
