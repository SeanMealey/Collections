#lang racket
(require racklog)

; space relations
(define %star
  (%rel ()
        [('sun)]
        [('sirius)]
        [('vega)]))

(define %orbits
  (%rel ()
        [('mercury 'sun)]
        [('venus 'sun)]
        [('earth 'sun)]
        [('mars 'sun)]
        [('jupiter 'sun)]
        [('saturn 'sun)]
        [('uranus 'sun)]
        [('neptune 'sun)]
        [('moon 'earth)]
        [('phobos 'mars)]
        [('deimos 'mars)]))
        
; space predicate
(define %spacePredicate 
   (%rel (p) 
   [(p) 
    (%orbits p 'sun)]))

; family parent relation
(define %parent
  (%rel ()
        [('Curtis 'William)]
        [('Margie 'William)]
        [('Curtis 'Carol)]
        [('Margie 'Carol)]
        [('Ernie 'Jennifer)]
        [('Carol 'Jennifer)]
        [('William 'Billy)]
        [('William 'Laura)]
        [('Mary 'Billy)]
        [('Mary 'Laura)]
        [('Laura 'Virginia)]
        [('Laura 'Miles)]
        ))

; family mystery relationship
(define %mysteryRelationship 
    (%rel (x y z) 
     [(x y) 
      (%parent z x) 
      (%parent z y) 
      (%/= x y)]))

; a student relation defining facts about students
(define %student
  (%rel ()
        [(123 35 2.75)]
        [(345 62 2.48)]
        [(456 90 3.62)]
        [(789 70 1.53)]))

; a (partially complete) satisfactoryProgress predicate
(define %satisfactoryProgress
  (%rel (id hours gpa)
        [(id)
         (%student id hours gpa)
         (%>= hours 90)
         (%>= gpa 1.90)]
        [(id)
         (%student id hours gpa)
         (%< hours 90)
         (%>= hours 60)
         (%>= gpa 1.80)]))


(%find-all (x) (%spacePredicate x))
(%which (earth) (%spacePredicate earth))