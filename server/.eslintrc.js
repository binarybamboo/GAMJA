module.exports = {
  plugins: ['prettier'],
  extends: ['airbnb-base', 'eslint:recommended', 'plugin:prettier/recommended'],
  parser: 'babel-eslint',
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'script',
    ecmaFeatures: {
      jsx: false,
    },
  },
  env: {
    browser: false,
    node: true,
  },
  ignorePatterns: ['node_modules/'],
  rules: {
    'no-console': 'off',
    // "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    // "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off"
    'prettier/prettier': [
      'error',
      {
        singleQuote: true,
        semi: true,
        useTabs: false,
        tabWidth: 2,
        trailingComma: 'all',
        printWidth: 80,
        endOfLine: 'auto',
        bracketSpacing: true,
        arrowParens: 'avoid',
      },
    ],
  },
};
