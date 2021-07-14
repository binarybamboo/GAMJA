const Joi = require('joi');

// const register = {
//   body: Joi.object().keys({
//     email: Joi.string().required().description('callback email querystring'),
//   }),
// };
const login = {
  body: Joi.object().keys({
    email: Joi.string().required().description('need email'),
  }),
};
const logout = {
  body: Joi.object().keys({
    refreshToken: Joi.string().required(),
  }),
};

const refreshTokens = {
  body: Joi.object().keys({
    refreshToken: Joi.string().required(),
  }),
};
module.exports = {
  login,
  logout,
  refreshTokens,
};
