/* eslint-disable no-underscore-dangle */
const httpStatus = require('http-status');
const mongoose = require('mongoose');
const { DiaryGroup } = require('../models');
const ApiError = require('../utils/ApiError');
const logger = require('../config/logger');

const createDiaryGroup = async req => {
  return DiaryGroup.create({
    name: req.body.name,
    description: req.body.description,
    author: mongoose.Types.ObjectId(req.user._id),
  });
};

const getDiaryGroups = async req => {
  return DiaryGroup.find({ author: req.user._id });
};

const updateDiaryGroupById = async req => {
  return DiaryGroup.findOneAndUpdate(
    {
      _id: req.params.diaryGroupId,
      author: mongoose.Types.ObjectId(req.user._id),
    },
    { $set: req.body },
    { returnNewDocument: true },
  );
};

const deleteDiaryGroupById = async req => {
  return DiaryGroup.deleteOne({
    _id: req.params.diaryGroupId,
    author: mongoose.Types.ObjectId(req.user._id),
  });
};

const addDiary = async (groupId, diaryId) => {
  const parent = await DiaryGroup.findOne({ _id: groupId });
  const array = [...parent.diary, diaryId];
  return parent.update({ diary: array });
};

const printAll = async id => {
  const group = await DiaryGroup.findOne({ _id: id }).populate(
    'diary',
    'photo state name description createdAt updatedAt',
  );
  return group.diary;
};

module.exports = {
  createDiaryGroup,
  getDiaryGroups,
  updateDiaryGroupById,
  deleteDiaryGroupById,
  addDiary,
  printAll,
};
