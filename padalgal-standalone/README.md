# Padalgal

A standalone, production-ready static Tamil music mood UI inspired by the reference at kudimagan.vercel.app, but with original branding, layout, styling and assets.

## Run locally
Open `index.html`, or serve the folder:
`python -m http.server 8080`

## Publish
Upload the folder to Vercel, Netlify, Cloudflare Pages or GitHub Pages. No server is required for the base experience.

## Important music-source note
Padalgal does not host or redistribute copyrighted audio. Clicking a track opens a YouTube search/listening source. You can later replace this with official YouTube embeds or a licensed Spotify/YouTube integration.

## New Release automation
`api/new-releases.json` is a replaceable feed. A production deployment should update it from a server-side job using the YouTube Data API or another licensed music provider. Keep API keys on the server, never in browser JS.
