import { existsSync } from "fs"

if (!existsSync(".env")) {
  console.error("  I did not see .env being here. You sure it was there?")
  console.error("  Copy .env.example to .env, edit .env, and try again.\n")
  process.exit(1)
}

process.exit(0)
