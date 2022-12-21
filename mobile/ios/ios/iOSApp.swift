import SwiftUI
import common

@main
struct iOSApp: App {
    
    @StateObject var state = State()
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .environmentObject(state)
		}
	}
}


class State: ObservableObject {
    @Published var appState = Store().instance.state as! AppState
    
    init() {
        print("haloooooo iniiiiiit")
        let _ = Store().instance.subscribe{
            print("bbbbbb")
            self.appState = Store().instance.state as! AppState
            return KotlinUnit()
        }
    }
}
