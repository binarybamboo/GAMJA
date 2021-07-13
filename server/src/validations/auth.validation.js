const Joi = require('joi');

const register = {
  body: Joi.object().keys({
    email: Joi.string().required().description('callback email querystring'),
  }),
};
module.exports = {
  register,
};
