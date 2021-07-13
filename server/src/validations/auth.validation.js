const Joi = require('joi');

const register = {
  body: Joi.object().keys({
    id: Joi.string().required().description('callback id querystring'),
  }),
};
module.exports = {
  register,
};
