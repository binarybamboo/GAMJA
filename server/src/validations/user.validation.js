const Joi = require('joi');

const changeName = {
  body: Joi.object().keys({
    email: Joi.string().email(),
  }),
};

module.exports = {};
