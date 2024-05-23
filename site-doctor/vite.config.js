import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

const VITE_ONLINE_HOSPITAL_GATEWAY_URL = "http://localhost";

// https://vitejs.dev/config/
export default defineConfig({
  base: "/doctor/",
  plugins: [vue()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url))
    }
  },
  server: {
    port: 3000,
    proxy: {
      "/system": {
        target: VITE_ONLINE_HOSPITAL_GATEWAY_URL,
        changeOrigin: true
      },
      "/user": {
        target: VITE_ONLINE_HOSPITAL_GATEWAY_URL,
        changeOrigin: true
      },
      "/hospital": {
        target: VITE_ONLINE_HOSPITAL_GATEWAY_URL,
        changeOrigin: true
      },
      "/registration": {
        target: VITE_ONLINE_HOSPITAL_GATEWAY_URL,
        changeOrigin: true
      },
      "/inquiry": {
        target: VITE_ONLINE_HOSPITAL_GATEWAY_URL,
        changeOrigin: true,
        ws: true
      }
    }
  }
});
