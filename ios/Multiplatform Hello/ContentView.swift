import SwiftUI
import Shared

struct ContentView: View {
    
    @State
    var message: String = "Loading"
    
    var body: some View {
        VStack {
            Text(message)
        }
        .padding()
        .onAppear {
            HelloKt.hello { message in
                self.message = message
            }
        }
    }
}

#Preview {
    ContentView()
}
