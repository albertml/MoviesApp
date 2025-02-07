import SwiftUI
import shared

@main
struct iOSApp: App {
  
  init() {
    KoinSetupKt.doInitKoin()
  }
  
	var body: some Scene {
		WindowGroup {
      MovieSearchView()
		}
	}
}
