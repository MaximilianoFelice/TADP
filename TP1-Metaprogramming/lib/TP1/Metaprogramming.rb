require "TP1/Prototyped"
require "TP1/Metaprogramming/Prototyped_Constructor"
require "TP1/Metaprogramming/version"

module TP

  class PrototypedObject
    include Prototyped
  end

  class ProtoConstructor
    include PrototypedConstructor
  end

end

