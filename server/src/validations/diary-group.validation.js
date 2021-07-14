const Joi = require('joi');
const { objectId } = require('./custom.validation');

const createDiaryGroup = {
  body: Joi.object().keys({
    name: Joi.string().required(),
    description: Joi.string().required().max(255),
  }),
};

const getDiaryGroups = {
  params: Joi.object().keys({}),
};

const updateDiaryGroup = {
  params: Joi.object().keys({
    diaryGroupId: Joi.required().custom(objectId),
  }),
  body: Joi.object()
    .keys({
      description: Joi.string().max(255),
      name: Joi.string(),
    })
    .min(1),
};

const deleteDiaryGroup = {
  params: Joi.object().keys({
    diaryGroupId: Joi.string().custom(objectId),
  }),
};

module.exports = {
  createDiaryGroup,
  getDiaryGroups,
  updateDiaryGroup,
  deleteDiaryGroup,
};
