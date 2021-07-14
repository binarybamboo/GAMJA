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
module.exports = {
  login,
};
