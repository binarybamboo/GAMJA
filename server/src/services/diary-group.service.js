/* eslint-disable no-underscore-dangle */
const httpStatus = require('http-status');
const mongoose = require('mongoose');
const { DiaryGroup } = require('../models');
const ApiError = require('../utils/ApiError');

const createDiaryGroup = async request => {
  return DiaryGroup.create({
    name: request.body.name,
    description: request.body.description,
    author: mongoose.Types.ObjectId(request.user._id),
  });
};

const getDiaryGroups = async request => {
  return DiaryGroup.find({ author: request.user._id });
};
const updateDiaryGroupById = async request => {
  return DiaryGroup.findOneAndUpdate(
    {
      _id: request.params.diaryGroupId,
      author: mongoose.Types.ObjectId(request.user._id),
    },
    { $set: request.body },
    { returnNewDocument: true },
  );
};

const deleteDiaryGroupById = async request => {
  return DiaryGroup.deleteOne({
    _id: request.params.diaryGroupId,
    author: mongoose.Types.ObjectId(request.user._id),
  });
};

module.exports = {
  createDiaryGroup,
  getDiaryGroups,
  updateDiaryGroupById,
  deleteDiaryGroupById,
};
