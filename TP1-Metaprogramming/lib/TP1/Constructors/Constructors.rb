require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"

module TP

  class Constructor

  end

  class CopyConstructor
    def build(new_object)
      new_object.prototype.clone
    end

    def arity
      0
    end
  end

  class HashConstructor
    def build(new_object, hash)
      hash.each{ |key, value| new_object.instance_module_variable_set(key, value) }
      new_object
    end

    def arity
      1
    end
  end

  class ProcConstructor

    attr_accessor :constructor

    def initialize(block)
      self.constructor = block
    end

    def build(new_object, *args)
      self.constructor.call(new_object, *args)
      new_object
    end

    def arity
      self.constructor.arity - 1
    end
  end

end
