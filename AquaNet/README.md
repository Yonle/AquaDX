# AquaNet

This is the codebase for the new frontend of AquaDX. 
This project is also heavily WIP, so more details will be added later on.

## Development

This project uses Svelte (NOT SvelteKit) + TypeScript + Sass, built using Vite. The preferred editor is VSCode.

### Running locally

You will need to have Node.js (or equivalent) and `pnpm` installed, Start by installing dependencies by running:
```
pnpm install
```

Then, you would need to start your testing AquaDX server and configure the `VITE_AQUA_HOST` in `.env` (copy `.env.example` file to `.env`) to use your URL.
Please leave `VITE_DATA_HOST` unchanged if you're not sure what it is.

Finally, run:
```
pnpm run dev
```
### Running locally (with bun)

Alternatively, If you prefer [Bun](https://bun.sh), You can do the same as above.
Start by configuring the `VITE_AQUA_HOST` in `.env` (copy `.env.example` file to `.env`) to use your URL.

and finally:
```
bun install
bun run dev
```
