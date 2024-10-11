# Gitlet Design Document

**Name**: Anna

## What is Gitlet
- Gitlet is a version-control system that mimics some of 
the basic features of the Git.
- A version-control system is essentially a backup system 
for related collections of files.

## What Gitlet can do
1. Commiting: Saving the contents of entire directories 
of files. the saved contents themselves are called commits.
2. Checking out: Restoring a version of one or more files 
or entire commits.
3. Log: Viewing the history of your backups.
4. Branches: Maintaining related sequences of commits.
5. Merging changes made in one branch into another.

## Classes and Data Structures

### Class 1

#### Fields

1. Field 1
2. Field 2


### Class 2

#### Fields

1. Field 1
2. Field 2


## Algorithms

## Persistence
```
.gitlet
├── blobs
├── commits
├── HEAD
├── index
└── refs
    ├── heads
    │   ├── master
    │   └── ...
    └── remotes
        ├── origin
        │   ├── master
        │   └── ...
        └── ...
```
    