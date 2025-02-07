//
//  MovieDetailsView.swift
//  iosApp
//
//  Created by Alberto Gaudicos Jr on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MovieDetailsView: View {
  @ObservedObject var movieDetailsViewModel = MovieDetailsViewModelWrapper()
   
  let imdbId: String

  var body: some View {
    ZStack {
      if movieDetailsViewModel.moviesState.isLoading {
        ProgressView()
      }
      
      if !movieDetailsViewModel.moviesState.error.isEmpty {
        Text(movieDetailsViewModel.moviesState.error )
      }
      
      if let movieDetails = movieDetailsViewModel.moviesState.movieDetails {
        ScrollView {
          VStack {
            AsyncImage(
              url: URL(string: movieDetails.poster),
              content: { image in
                image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(height: 300)
                .clipped() },
              placeholder: { ProgressView() }
            )
            
            VStack(alignment: .leading) {
              Text("Title: " + movieDetails.title)
                .font(.title)
                .fontWeight(.bold)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              Text("Plot: " + movieDetails.plot)
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              Text("Language: " + movieDetails.language)
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              
              Text("Genre: " +  movieDetails.genre)
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              Text("Director: " + movieDetails.director)
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              Text("Released: " + movieDetails.released)
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
              
              
              Text("Rated: " +  movieDetails.rated )
                .font(.body)
                .foregroundColor(.black)
              
              Spacer(minLength: 12)
            }
            .padding(.horizontal, 8)
          }
          .ignoresSafeArea()
        }
      }
    }
    .onAppear {
      movieDetailsViewModel.startObserving()
      movieDetailsViewModel.viewModel.getMovieDetails(movieId: imdbId)
    }

  }
}

#Preview {
  MovieDetailsView(imdbId: "0")
}
