const Joi = require('joi');
const { objectId } = require('./custom.validation');

const createDiary = {
  body: Joi.object().keys({
    name: Joi.string(),
    description: Joi.string().max(255),
    diaryGroupId: Joi.string(),
  }),
};

const getDiary = {
  body: Joi.object().keys({
    diaryGroupId: Joi.required().custom(objectId),
  }),
};

const updateDiary = {
  params: Joi.object().keys({
    diaryId: Joi.required().custom(objectId),
  }),
  body: Joi.object()
    .min(1)
    .keys({
      name: Joi.string(),
      description: Joi.string().max(255),
    }),
};

const deleteDiary = {
  params: Joi.object().keys({
    diaryId: Joi.string().custom(objectId),
  }),
};
module.exports = {
  createDiary,
  getDiary,
  deleteDiary,
  updateDiary,
};
