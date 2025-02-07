//
//  MoviesViewModelWrapper.swift
//  iosApp
//
//  Created by Alberto Gaudicos Jr on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

@MainActor
class MoviesViewModelWrapper: ObservableObject {
  var viewModel: MoviesViewModel

  init() {
    viewModel = ProvideViewModel.shared.getMoviesViewModel()
    moviesState = viewModel.uiState.value
  }

  @Published var moviesState: UiState
  @Published var query: String = "" {
    didSet {
      viewModel.updateQuery(s: query)
    }
  }

  func startObserving() {
    Task {
      for await movieS in viewModel.uiState {
        self.moviesState = movieS
      }
    }
  }
}
