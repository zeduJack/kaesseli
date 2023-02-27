import SwiftUI
import common

@main
struct KaesseliApp: App {
    
    @StateObject var state = State()
    
    init() {
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor : UIColor.red]
    }
    
	var body: some Scene {
		WindowGroup {
			LoginScreen().environmentObject(state)
		}
	}
}


class State: ObservableObject {
    @Published var appState = Store().instance.state as! AppState
    
    init() {
        let _ = Store().instance.subscribe{
            self.appState = Store().instance.state as! AppState
            return KotlinUnit()
        }
    }
}
