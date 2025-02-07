//
//  MovieSearchView.swift
//  iosApp
//
//  Created by Alberto Gaudicos Jr on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MovieSearchView: View {
  @ObservedObject var moviesViewModelWrapper = MoviesViewModelWrapper()

  var body: some View {
    NavigationView {
      VStack {
//        if moviesViewModelWrapper.moviesState.isLoading {
//          ProgressView()
//        }

        if !moviesViewModelWrapper.moviesState.error.isEmpty {
          Text(moviesViewModelWrapper.moviesState.error)
        }

        if let movies = moviesViewModelWrapper.moviesState.movies {
          List(movies, id: \.self) { item in

            VStack(alignment: .leading) {
              AsyncImage(
                url: URL(string: item.poster),
                content: { image in
                  image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(height: 300)
                    .clipped()
                },
                placeholder: { ProgressView() }
              )

              Text(item.title)
                .font(.title)
                .fontWeight(.bold)
                .foregroundColor(.black)
            }
            .ignoresSafeArea()
            .padding(.vertical, 8)
            .background(NavigationLink(destination: {
              MovieDetailsView(imdbId: item.imdbID)
            }, label: {
              // noop
            }))
          }
          .listStyle(PlainListStyle())
          .padding(0)
        }
      }
      .onAppear {
        moviesViewModelWrapper.startObserving()
        moviesViewModelWrapper.viewModel.getMovies(search: "Naruto")
      }
      .searchable(text: $moviesViewModelWrapper.query, placement: .navigationBarDrawer(displayMode: .always))
    }
  }
}

#Preview {
  MovieSearchView()
}

