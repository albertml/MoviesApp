//
//  MovieDetailsViewModelWrapper.swift
//  iosApp
//
//  Created by Alberto Gaudicos Jr on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared
import SwiftUI

@MainActor
class MovieDetailsViewModelWrapper: ObservableObject {
  var viewModel: MovieDetailsViewModel

  init() {
    viewModel = ProvideViewModel.shared.getMovieDetailViewModel()
    moviesState = viewModel.uiState.value
  }

  @Published var moviesState: DetailUiState

  func startObserving() {
    Task {
      for await movieS in viewModel.uiState {
        self.moviesState = movieS
      }
    }
  }
}
