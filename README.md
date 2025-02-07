# OMDb API Integration

The OMDb API is a service that allows you to fetch data about movies, series, and episodes.

<table>
  <tr>
    <td><img src="android.png" height="400"></td>
    <td><img src="ios.png" height="400"></td>
  </tr>
</table>

## Features
- Retrieve movie data by title or IMDb ID.
- Access movie posters.
- Returns results in JSON or XML.

## Setup

1. **Clone the repository**:
    ```bash
    git clone https://gitlab.com/your-repository.git
    ```

2. **Set up environment**:
    - Create a `.env` file and add your API key:
    ```bash
    OMDB_API_KEY=your_api_key
    ```

## Usage

### By Title
```bash
curl "http://www.omdbapi.com/?t=Inception&apikey=your_api_key"
```

### By IMDb ID
```bash
curl "http://www.omdbapi.com/?i=tt1375666&apikey=your_api_key"
```

## Parameters
	•	t: Movie title (optional)
	•	i: IMDb ID (optional)
	•	type: Type of result (movie, series, episode)
	•	y: Release year (optional)
	•	plot: Plot length (short or full)
	•	r: Response format (json, xml)

